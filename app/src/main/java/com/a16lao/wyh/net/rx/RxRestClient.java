package com.a16lao.wyh.net.rx;


/**
 * author: caoyan
 * time:2018/5/4 11:49
 * description:
 */
public class RxRestClient {
//    private final String URL;
//    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
//    private final RequestBody BODY;
//    private final File FILE;
//    private final LoaderStyle LOADER_STYLE;
//    private final Context context;
//
//    public RxRestClient(String url,
//                        Map<String, Object> params,
//                        RequestBody body,
//                        File file,
//                        Context context,
//                        LoaderStyle loader_style) {
//        this.URL = url;
//        PARAMS.putAll(params);
//        this.BODY = body;
//        this.FILE = file;
//        this.context = context;
//        this.LOADER_STYLE = loader_style;
//    }
//
//    public static RxRestClientBuilder builder() {
//        return new RxRestClientBuilder();
//    }
//
//    private Observable<String> request(HttpMethod method) {
//        final RxRestService service = RestCreator.getRxRestService();
//        Observable<String> observable = null;
//
//        if (LOADER_STYLE != null) {
//            DefaultDialog.showLoading(context, LOADER_STYLE);
//        }
//
//        switch (method) {
//            case GET:
//                observable = service.get(URL, PARAMS);
//                break;
//            case POST:
//                observable = service.post(URL, PARAMS);
//                break;
//            case POST_RAW:
//                observable = service.postRaw(URL, BODY);
//                break;
//            case PUT:
//                observable = service.put(URL, PARAMS);
//                break;
//            case PUT_RAW:
//                observable = service.putRaw(URL, BODY);
//                break;
//            case DELETE:
//                observable = service.delete(URL, PARAMS);
//                break;
//            case UPLOAD:
//                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
//                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
//                observable = RestCreator.getRxRestService().upload(URL, body);
//                break;
//            default:
//                break;
//
//        }
//
//        return observable;
//
//    }
//
//    public final Observable<String> get() {
//        return request(HttpMethod.GET);
//    }
//
//    public final Observable<String> post() {
//        if (BODY == null) {
//            return request(HttpMethod.POST);
//        } else {
//            if (!PARAMS.isEmpty()) {
//                throw new RuntimeException("params must be null");
//            }
//            return request(HttpMethod.POST_RAW);
//        }
//
//    }
//
//    public final Observable<String> put() {
//        if (BODY == null) {
//            return request(HttpMethod.PUT);
//        } else {
//            if (!PARAMS.isEmpty()) {
//                throw new RuntimeException("params must be null");
//            }
//            return request(HttpMethod.PUT_RAW);
//        }
//    }
//
//    public final Observable<String> delete() {
//        return request(HttpMethod.DELETE);
//    }
//
//    public final Observable<ResponseBody> download() {
//        return RestCreator.getRxRestService().download(URL, PARAMS);
//    }
}
