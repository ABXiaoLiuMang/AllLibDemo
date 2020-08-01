//package com.dale.net_demo;
//
//
//import java.io.IOException;
//import java.util.Set;
//
//import okhttp3.HttpUrl;
//import okhttp3.Interceptor;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//import okio.Buffer;
//
//public class ParameIntercept implements Interceptor {
//    private static final String METHOD_GET = "GET";
//    private static final String METHOD_POST = "POST";
//
//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        Request request = chain.request();
//        Request.Builder requestBuilder = request.newBuilder();
//        HttpUrl.Builder urlBuilder = request.url().newBuilder();
//
//
//        requestBuilder.addHeader("X-KK-NONCE", nonce);
//        requestBuilder.addHeader("X-KK-TIMESTAMP", String.valueOf(timestamp));
//        if (METHOD_GET.equals(request.method())) {
////            urlBuilder.addEncodedQueryParameter("dale", "getparams");
//            HttpUrl httpUrl = urlBuilder.build();
//            Set<String> paramKeys = httpUrl.queryParameterNames();
//            for (String key : paramKeys) {
//                String value = httpUrl.queryParameter(key);
//                LogUtils.d(key + " " + value);
//            }
//            requestBuilder.url(httpUrl);
//        } else if (METHOD_POST.equals(request.method())) {
//            Buffer buffer = new Buffer();
//            RequestBody requestBody = request.body();
//            requestBody.writeTo(buffer);
//            String jsonParam = buffer.readByteString().utf8();
//
////            FormBody.Builder bodyBuilder = new FormBody.Builder();
////            if (request.body() instanceof FormBody) { //把已有的post参数添加到新的构造器
////                FormBody formBody = (FormBody) request.body();
////                for (int i = 0; i < formBody.size(); i++) {
////                    bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
////                }
////            }
////            bodyBuilder.addEncoded("dale", "postparams");//这里可以添加一些公共post参数
////            FormBody newBody = bodyBuilder.build();
////            requestBuilder.post(newBody);
//        }
//        return chain.proceed(requestBuilder.build());
//    }
//
//
//
//
//
//
//}
