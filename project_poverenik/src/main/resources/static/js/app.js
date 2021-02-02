const router = new VueRouter({
    mode: 'hash',
    routes: [
        { path: '/create', component: HomePage },
        { path: '/login', component: LoginPage },
        { path: '/zalba-na-cutanje', component: ZalbaNaCutanje },
        { path: '/zalba-na-odluku', component: ZalbaNaOdluku },
        { path: '/search', component: RequestsSearchPage },
        { path: '/advance-search', component: RequestsAdvanceSearchPage },
        { path: '/search-complaint-res', component: ComplaintResolutionSearchPage },
        { path: '/advance-search-complaint-res', component: ComplaintResolutionAdvanceSearchPage },
         { path: '/complaint-res-list', component: ComplaintResolutionTablePage },
        { path: '/create-resolution', component: NewResolutionPage },
        { path: '/resolution-list', component: ResolutionTablePage },
        { path: '/', component: RequestsTablePage }
    ]
})

var app = new Vue({
    router,
    el: '#app'
})