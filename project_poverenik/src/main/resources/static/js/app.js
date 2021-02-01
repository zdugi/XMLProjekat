const router = new VueRouter({
    mode: 'hash',
    routes: [
        { path: '/create', component: HomePage },
        { path: '/login', component: LoginPage },
        { path: '/zalba-na-cutanje', component: ZalbaNaCutanje },
        { path: '/zalba-na-odluku', component: ZalbaNaOdluku },
        { path: '/search', component: RequestsSearchPage },
        { path: '/advance-search', component: RequestsAdvanceSearchPage },
        { path: '/', component: RequestsTablePage }
    ]
})

var app = new Vue({
    router,
    el: '#app'
})