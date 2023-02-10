package hanmangul.api.common.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ApiFilterResponseWrapper extends HttpServletResponseWrapper {

    ByteArrayOutputStream byteArrayOutputStream;
    ApiOutputStream apiOutputStream;

    public ApiFilterResponseWrapper(HttpServletResponse response) {
        super(response);
        byteArrayOutputStream = new ByteArrayOutputStream();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (apiOutputStream == null) {
            apiOutputStream = new ApiOutputStream(byteArrayOutputStream);
        }
        return apiOutputStream;
    }

    public String getDataStreamToString() {
        return new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
    }

    public static class ApiOutputStream extends ServletOutputStream {
        private final DataOutputStream outputStream;

        public ApiOutputStream(OutputStream output) {
            this.outputStream = new DataOutputStream(output);
        }

        @Override
        public void write(int b) throws IOException {
            outputStream.write(b);
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener listener) {
        }
    }

}
