package com.joeys.wanandroid.network

import com.joeys.wanandroid.data.*
import retrofit2.http.*


/**
 * Created by chenxz on 2018/4/21.
 */
interface Api {

    /**
     * 获取轮播图
     * http://www.wanandroid.com/banner/json
     */
    @GET("banner/json")
    suspend fun getBanners(): HttpResult<List<Banner>>

    /**
     * 获取首页置顶文章列表
     * http://www.wanandroid.com/article/top/json
     */
    @GET("article/top/json")
    suspend fun getTopArticles(): HttpResult<MutableList<Article>>

    /**
     * 获取文章列表
     * http://www.wanandroid.com/article/list/0/json
     * @param pageNum
     */
    @GET("article/list/{pageNum}/json")
    suspend fun getArticles(@Path("pageNum") pageNum: Int): HttpResult<ArticleResponseBody>

    /**
     * 获取知识体系
     * http://www.wanandroid.com/tree/json
     */
    @GET("tree/json")
    suspend fun getKnowledgeTree(): HttpResult<List<KnowledgeTreeBody>>

    /**
     * 知识体系下的文章
     * http://www.wanandroid.com/article/list/0/json?cid=168
     * @param page
     * @param cid
     */
    @GET("article/list/{page}/json")
    suspend fun getKnowledgeList(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): HttpResult<ArticleResponseBody>

    /**
     * 导航数据
     * http://www.wanandroid.com/navi/json
     */
    @GET("navi/json")
    suspend fun getNavigationList(): HttpResult<List<NavigationBean>>

    /**
     * 项目数据
     * http://www.wanandroid.com/project/tree/json
     */
    @GET("project/tree/json")
    suspend fun getProjectTree(): HttpResult<List<ProjectTreeBean>>

    /**
     * 项目列表数据
     * http://www.wanandroid.com/project/list/1/json?cid=294
     * @param page
     * @param cid
     */
    @GET("project/list/{page}/json")
    suspend fun getProjectList(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): HttpResult<ArticleResponseBody>

    /**
     * 登录
     * http://www.wanandroid.com/user/login
     * @param username
     * @param password
     */
    @POST("user/login")
    @FormUrlEncoded
    suspend fun loginWanAndroid(
        @Field("username") username: String,
        @Field("password") password: String
    ): HttpResult<LoginData>

    /**
     * 注册
     * http://www.wanandroid.com/user/register
     * @param username
     * @param password
     * @param repassword
     */
    @POST("user/register")
    @FormUrlEncoded
    suspend fun registerWanAndroid(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): HttpResult<LoginData>

    /**
     * 退出登录
     * http://www.wanandroid.com/user/logout/json
     */
    @GET("user/logout/json")
    suspend fun logout(): HttpResult<Any>

    /**
     *  获取收藏列表
     *  http://www.wanandroid.com/lg/collect/list/0/json
     *  @param page
     */
    @GET("lg/collect/list/{page}/json")
    suspend fun getCollectList(@Path("page") page: Int): HttpResult<BaseListResponseBody<CollectionArticle>>

    /**
     * 收藏站内文章
     * http://www.wanandroid.com/lg/collect/1165/json
     * @param id article id
     */
    @POST("lg/collect/{id}/json")
    suspend fun addCollectArticle(@Path("id") id: Int): HttpResult<Any>

    /**
     * 收藏站外文章
     * http://www.wanandroid.com/lg/collect/add/json
     * @param title
     * @param author
     * @param link
     */
    @POST("lg/collect/add/json")
    @FormUrlEncoded
    suspend fun addCoolectOutsideArticle(
        @Field("title") title: String,
        @Field("author") author: String,
        @Field("link") link: String
    ): HttpResult<Any>

    /**
     * 文章列表中取消收藏文章
     * http://www.wanandroid.com/lg/uncollect_originId/2333/json
     * @param id
     */
    @POST("lg/uncollect_originId/{id}/json")
    suspend fun cancelCollectArticle(@Path("id") id: Int): HttpResult<Any>

    /**
     * 收藏列表中取消收藏文章
     * http://www.wanandroid.com/lg/uncollect/2805/json
     * @param id
     * @param originId
     */
    @POST("lg/uncollect/{id}/json")
    @FormUrlEncoded
    suspend fun removeCollectArticle(
        @Path("id") id: Int,
        @Field("originId") originId: Int = -1
    ): HttpResult<Any>

    /**
     * 搜索热词
     * http://www.wanandroid.com/hotkey/json
     */
    @GET("hotkey/json")
    suspend fun getHotSearchData(): HttpResult<MutableList<HotSearchBean>>

    /**
     * 搜索
     * http://www.wanandroid.com/article/query/0/json
     * @param page
     * @param key
     */
    @POST("article/query/{page}/json")
    @FormUrlEncoded
    suspend fun queryBySearchKey(
        @Path("page") page: Int,
        @Field("k") key: String
    ): HttpResult<ArticleResponseBody>

    /**
     * 获取TODO列表数据
     * http://wanandroid.com/lg/todo/list/0/json
     * @param type
     */
    @POST("/lg/todo/list/{type}/json")
    suspend fun getTodoList(@Path("type") type: Int): HttpResult<AllTodoResponseBody>

    /**
     * 获取未完成Todo列表
     * http://wanandroid.com/lg/todo/listnotdo/0/json/1
     * @param type 类型拼接在链接上，目前支持0,1,2,3
     * @param page 拼接在链接上，从1开始
     */
    @POST("/lg/todo/listnotdo/{type}/json/{page}")
    suspend fun getNoTodoList(
        @Path("page") page: Int,
        @Path("type") type: Int
    ): HttpResult<TodoResponseBody>

    /**
     * 获取已完成Todo列表
     * http://www.wanandroid.com/lg/todo/listdone/0/json/1
     * @param type 类型拼接在链接上，目前支持0,1,2,3
     * @param page 拼接在链接上，从1开始
     */
    @POST("/lg/todo/listdone/{type}/json/{page}")
    suspend fun getDoneList(
        @Path("page") page: Int,
        @Path("type") type: Int
    ): HttpResult<TodoResponseBody>

    /**
     * V2版本 ： 获取TODO列表数据
     * http://www.wanandroid.com/lg/todo/v2/list/页码/json
     * @param page 页码从1开始，拼接在 url 上
     * @param map
     *          status 状态， 1-完成；0未完成; 默认全部展示；
     *          type 创建时传入的类型, 默认全部展示
     *          priority 创建时传入的优先级；默认全部展示
     *          orderby 1:完成日期顺序；2.完成日期逆序；3.创建日期顺序；4.创建日期逆序(默认)；
     */
    @GET("/lg/todo/v2/list/{page}/json")
    suspend fun getTodoList(
        @Path("page") page: Int,
        @QueryMap map: MutableMap<String, Any>
    ): HttpResult<AllTodoResponseBody>

    /**
     * 仅更新完成状态Todo
     * http://www.wanandroid.com/lg/todo/done/80/json
     * @param id 拼接在链接上，为唯一标识
     * @param status 0或1，传1代表未完成到已完成，反之则反之
     */
    @POST("/lg/todo/done/{id}/json")
    @FormUrlEncoded
    suspend fun updateTodoById(@Path("id") id: Int, @Field("status") status: Int): HttpResult<Any>

    /**
     * 删除一条Todo
     * http://www.wanandroid.com/lg/todo/delete/83/json
     * @param id
     */
    @POST("/lg/todo/delete/{id}/json")
    suspend fun deleteTodoById(@Path("id") id: Int): HttpResult<Any>

    /**
     * 新增一条Todo
     * http://www.wanandroid.com/lg/todo/add/json
     * @param body
     *          title: 新增标题
     *          content: 新增详情
     *          date: 2018-08-01
     *          type: 0
     */
    @POST("/lg/todo/add/json")
    @FormUrlEncoded
    suspend fun addTodo(@FieldMap map: MutableMap<String, Any>): HttpResult<Any>

    /**
     * 更新一条Todo内容
     * http://www.wanandroid.com/lg/todo/update/83/json
     * @param body
     *          title: 新增标题
     *          content: 新增详情
     *          date: 2018-08-01
     *          status: 0 // 0为未完成，1为完成
     *          type: 0
     */
    @POST("/lg/todo/update/{id}/json")
    @FormUrlEncoded
    suspend fun updateTodo(
        @Path("id") id: Int,
        @FieldMap map: MutableMap<String, Any>
    ): HttpResult<Any>

    /**
     * 获取公众号列表
     * http://wanandroid.com/wxarticle/chapters/json
     */
    @GET("/wxarticle/chapters/json")
    suspend fun getWXChapters(): HttpResult<MutableList<WXChapterBean>>

    /**
     * 查看某个公众号历史数据
     * http://wanandroid.com/wxarticle/list/405/1/json
     * @param id 公众号 ID
     * @param page 公众号页码
     */
    @GET("/wxarticle/list/{id}/{page}/json")
    suspend fun getWXArticles(
        @Path("id") id: Int,
        @Path("page") page: Int
    ): HttpResult<ArticleResponseBody>

    /**
     * 在某个公众号中搜索历史文章
     * http://wanandroid.com/wxarticle/list/405/1/json?k=Java
     * @param id 公众号 ID
     * @param key 搜索关键字
     * @param page 公众号页码
     */
    @GET("/wxarticle/list/{id}/{page}/json")
    suspend fun queryWXArticles(
        @Path("id") id: Int,
        @Query("k") key: String,
        @Path("page") page: Int
    ): HttpResult<ArticleResponseBody>

    /**
     * 获取个人积分，需要登录后访问
     * https://www.wanandroid.com/lg/coin/userinfo/json
     */
    @GET("/lg/coin/userinfo/json")
    suspend fun getUserInfo(): HttpResult<UserInfoBody>

    /**
     * 获取个人积分列表，需要登录后访问
     * https://www.wanandroid.com//lg/coin/list/1/json
     * @param page 页码 从1开始
     */
    @GET("/lg/coin/list/{page}/json")
    suspend fun getUserScoreList(@Path("page") page: Int): HttpResult<BaseListResponseBody<UserScoreBean>>

    /**
     * 获取积分排行榜
     * https://www.wanandroid.com/coin/rank/1/json
     * @param page 页码 从1开始
     */
    @GET("/coin/rank/{page}/json")
    suspend fun getRankList(@Path("page") page: Int): HttpResult<BaseListResponseBody<CoinInfoBean>>

    /**
     * 自己的分享的文章列表
     * https://wanandroid.com/user/lg/private_articles/1/json
     * @param page 页码 从1开始
     */
    @GET("user/lg/private_articles/{page}/json")
    suspend fun getShareList(@Path("page") page: Int): HttpResult<ShareResponseBody>

    /**
     * 分享文章
     * https://www.wanandroid.com/lg/user_article/add/json
     * @param map
     *      title: 文章标题
     *      link:  文章链接
     */
    @POST("lg/user_article/add/json")
    @FormUrlEncoded
    suspend fun shareArticle(@FieldMap map: MutableMap<String, Any>): HttpResult<Any>

    /**
     * 删除自己分享的文章
     * https://wanandroid.com/lg/user_article/delete/9475/json
     * @param id 文章id，拼接在链接上
     */
    @POST("lg/user_article/delete/{id}/json")
    suspend fun deleteShareArticle(@Path("id") id: Int): HttpResult<Any>

    /**
     * 广场列表数据
     * https://wanandroid.com/user_article/list/0/json
     * @param page 页码拼接在url上从0开始
     */
    @GET("user_article/list/{page}/json")
    suspend fun getSquareList(@Path("page") page: Int): HttpResult<ArticleResponseBody>

}