package hanmangul.common.utils.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
public class HttpUtils {
    public static enum HttpMethodType {
        POST, GET, DELETE, PUT
    }

    /**
     * 기본적으로 json 요청
     */
    public static String post(String requestUrl, String jsonObjStr) {
        HttpURLConnection conn = connect(requestUrl);
        HttpConfig config = new HttpConfig();
        setConfig(conn, config);
        send(conn, jsonObjStr);
        return receive(conn);
    }

    public static String post(String requestUrl, String jsonObjStr, HttpConfig config) {
        HttpURLConnection conn = connect(requestUrl);
        setConfig(conn, config);
        send(conn, jsonObjStr);
        return receive(conn);
    }

    public static String postForm(String requestUrl, String jsonObjStr) {
        HttpURLConnection conn = connect(requestUrl);
        HttpConfig config = new HttpConfig();
        config.setContentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        setConfig(conn, config);
        send(conn, jsonObjStr);
        return receive(conn);
    }

    private static void setConfig(HttpURLConnection conn, HttpConfig config) {
        try {
            // 전송모드 설정(일반적인 POST방식)
            conn.setDefaultUseCaches(config.isUseCache());
            conn.setDoInput(config.isDoInput());
            conn.setDoOutput(config.isDoOutput());
            conn.setConnectTimeout(config.getConnectTimeOut());
            conn.setReadTimeout(config.getReadTimeOut());
            conn.setRequestMethod(config.getMethod());
            conn.setRequestProperty("charset", "UTF-8");

            // content-type 설정
            conn.setRequestProperty("Content-type", config.getContentType());
            conn.setRequestProperty("Accept", "application/json");
            HashMap<String, String> headers = config.getHeaders();
            if (headers.size() > 0) {
                Set<String> keySet = headers.keySet();
                for (String key : keySet) {
                    String value = headers.get(key);
                    conn.setRequestProperty(key, value);
                }
            }
        } catch (Exception e) {
            log.error("", e);
        }
    }

    private static HttpURLConnection connect(String requestUrl) {
        HttpURLConnection http = null;
        try {
            URL url = new URL(requestUrl);

            // URL설정, 접속
            if (requestUrl.startsWith("https")) {
                TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                } };

                // Install the all-trusting trust manager
                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(null, trustAllCerts, new java.security.SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

                // Create all-trusting host name verifier
                HostnameVerifier allHostsValid = new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                };

                // install the all-trusting host verifier
                HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
                http = (HttpsURLConnection) url.openConnection();
            } else {
                http = (HttpURLConnection) url.openConnection();
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return http;
    }

    private static void send(HttpURLConnection conn, String dataStr) {

        OutputStream os = null;
        try {
            os = conn.getOutputStream();
            if (StringUtils.isNotEmpty(dataStr)) {
                byte[] data = dataStr.getBytes(StandardCharsets.UTF_8);
                if (data.length > 0) {
                    os.write(data);
                    os.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e1) {
                    log.error(e1.getMessage());
                }
            }
        }
    }

    private static String receive(HttpURLConnection conn) {
        InputStream inputStream = null;
        String result = "";
        try {
            inputStream = conn.getInputStream();
            InputStreamReader in = new InputStreamReader(inputStream);
            BufferedReader bufferReader = new BufferedReader(in);
            StringBuilder builder = new StringBuilder();
            String str;
            while ((str = bufferReader.readLine()) != null) {
                builder.append(str).append("\n");
            }
            bufferReader.close();
            in.close();
            result = builder.toString();
        } catch (Exception e) {
            log.error("", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
        }

        return result;
    }

    public String send2(final HttpMethodType httpMethod, final String apiPath, final String params, String token) throws KeyManagementException, NoSuchAlgorithmException {
        return request2(httpMethod, apiPath, params, token);
    }

    public String request2(HttpMethodType httpMethod, final String apiPath, final String params, String token) throws NoSuchAlgorithmException, KeyManagementException {

        String requestUrl = apiPath;
        if (httpMethod == null) {
            httpMethod = HttpMethodType.GET;
        }
        if (params != null && params.length() > 0 && (httpMethod == HttpMethodType.GET || httpMethod == HttpMethodType.DELETE)) {
            requestUrl += params;
        }
        OutputStreamWriter writer = null;
        BufferedReader reader = null;
        InputStreamReader isr = null;

        try {

            if (apiPath.contains("http:")) {
                HttpURLConnection conn;

                final URL url = new URL(requestUrl);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod(httpMethod.toString());
                if (token == null) {
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;");
                } else if (token.equals("json")) {
                    conn.setRequestProperty("Content-Type", "application/json");
                } else {
                    conn.setRequestProperty("Content-Type", "application/json");
                    /* conn.setRequestProperty("Accept", "application/json"); */
                    conn.setRequestProperty("bt-token", token);
                }
                conn.setRequestProperty("charset", "utf-8");
                conn.setUseCaches(false);

                if (params != null && params.length() > 0 && (httpMethod == HttpMethodType.POST || httpMethod == HttpMethodType.PUT)) {

                    conn.setDoOutput(true);
                    writer = new OutputStreamWriter(conn.getOutputStream());
                    writer.write(params);
                    System.out.println(requestUrl);
                    System.out.println(params);
                    System.out.println(writer);
                    System.out.println(conn);
                    writer.flush();
                }

                final int responseCode = conn.getResponseCode();
                System.out.println(String.format("\nSending '%s' request to URL : %s", httpMethod, requestUrl));
                System.out.println("Response Code : " + responseCode);
                if (responseCode == 200)
                    isr = new InputStreamReader(conn.getInputStream());
                else
                    isr = new InputStreamReader(conn.getErrorStream());

                reader = new BufferedReader(isr);
                final StringBuffer buffer = new StringBuffer();
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                System.out.println(buffer.toString());

                return buffer.toString();
            } else {
                HttpsURLConnection conn;

                final URL url = new URL(requestUrl);
                conn = (HttpsURLConnection) url.openConnection();
                conn.setRequestMethod(httpMethod.toString());
                if (token == null) {
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;");
                } else if (token.equals("json")) {
                    conn.setRequestProperty("Content-Type", "application/json");
                } else {
                    conn.setRequestProperty("Content-Type", "application/json");
                    /* conn.setRequestProperty("Accept", "application/json"); */
                    conn.setRequestProperty("bt-token", token);
                }
                conn.setRequestProperty("charset", "utf-8");
                conn.setUseCaches(false);

                if (params != null && params.length() > 0 && (httpMethod == HttpMethodType.POST || httpMethod == HttpMethodType.PUT)) {

                    conn.setDoOutput(true);
                    writer = new OutputStreamWriter(conn.getOutputStream());
                    writer.write(params);
                    System.out.println(requestUrl);
                    System.out.println(params);
                    System.out.println(writer);
                    System.out.println(conn);
                    writer.flush();
                }

                final int responseCode = conn.getResponseCode();
                System.out.println(String.format("\nSending '%s' request to URL : %s", httpMethod, requestUrl));
                System.out.println("Response Code : " + responseCode);
                if (responseCode == 200)
                    isr = new InputStreamReader(conn.getInputStream());
                else
                    isr = new InputStreamReader(conn.getErrorStream());

                reader = new BufferedReader(isr);
                final StringBuffer buffer = new StringBuffer();
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                System.out.println(buffer.toString());

                return buffer.toString();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null)
                try {
                    writer.close();
                } catch (Exception ignore) {
                }
            if (reader != null)
                try {
                    reader.close();
                } catch (Exception ignore) {
                }
            if (isr != null)
                try {
                    isr.close();
                } catch (Exception ignore) {
                }
        }
        return null;
    }
}
