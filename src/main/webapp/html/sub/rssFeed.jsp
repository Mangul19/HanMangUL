<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.net.URL"%>
<%@ page import="java.net.URLConnection"%>
<%@ page import="java.net.UnknownHostException"%>
<%@ page import="org.json.*" %>
<% 

	URLConnection con = null;
	InputStreamReader isr = null;
	BufferedReader br = null;
	StringBuffer sb = null;
	
	ServletOutputStream sos = null;
	BufferedOutputStream bos = null;
	
	try{
	    
	  String urlString = "https://www.daegu.go.kr/icms/rss/feed.do?";
	  
	  String id = request.getParameter("id");
	  
	  urlString = urlString + "id=" + id;
	  
	  URL u = new URL(urlString);
	  con = u.openConnection();
	  con.connect();
	
	  isr = new InputStreamReader(con.getInputStream(),"UTF-8");
	  br = new BufferedReader(isr);
	  
	  sb = new StringBuffer();
	  String line  = null;
	  while((line = br.readLine()) != null){
	    sb.append(line+"\n");
	  }
	  String cont  = sb.toString().trim(); // The processing instruction target matching "[xX][mM][lL]" is not allowed 오류 제거 위해 trim()
	  
	  JSONObject jsonObject = XML.toJSONObject(cont);
	  
	  JSONObject outputJson = new JSONObject();
	  outputJson.put("code", "SUCCESS");
	  outputJson.put("status", "200");
	  outputJson.put("message", "success");
	  outputJson.put("data", jsonObject.get("rss"));
	  
	  response.setContentType("application/json");
	  out.print(outputJson.toString());
	  /* sos = response.getOutputStream();
	  bos = new BufferedOutputStream(sos);
	  byte[] bs = cont.getBytes(); 
	  bos.write(bs, 0, bs.length);
	  bos.flush(); */
	
	  
	}catch(UnknownHostException uhe){
	  StringBuffer  sbLog = new StringBuffer(); // FileAccess 를 줄이기 위해서 한번에 Write 하는 방식
	  sbLog.append("\n+++."+request.getRequestURL()+  "..\n");
	  sbLog.append("+++.Exception:" +uhe.toString()+    "..\n");
	  System.out.println(sbLog);                // println 이기 때문에 맨 뒤에는 sbLog.append("\n"); 불필요.
	  sbLog.delete(0,sbLog.length());
	}catch(Exception e){
	  StringBuffer  sbLog = new StringBuffer(); // FileAccess 를 줄이기 위해서 한번에 Write 하는 방식
	  sbLog.append("\n+++."+request.getRequestURL()+  "..\n");
	  sbLog.append("+++.Exception:" +e.toString()+    "..\n");
	  System.out.println(sbLog);                // println 이기 때문에 맨 뒤에는 sbLog.append("\n"); 불필요.
	  sbLog.delete(0,sbLog.length());
	}finally{
	  if(sb !=null) try{ sb.delete(0, sb.length()); }catch(Exception e){}finally{ sb =null; }
	  if(br !=null) try{ br.close(); }catch(Exception e){}finally{ br =null; }
	  if(isr !=null) try{ isr.close(); }catch(Exception e){}finally{ isr =null; }
	
	  if(sos !=null) try{ sos.close(); }catch(Exception e){}finally{ sos =null; }
	  if(bos !=null) try{ bos.close(); }catch(Exception e){}finally{ bos =null; }
	}

%>