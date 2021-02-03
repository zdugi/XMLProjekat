const router = new VueRouter({
    mode: 'hash',
    routes: [
        { path: '/requests-table-page', component: RequestsTablePage },
        { path: '/poverenik', component: PoverenikPage },
        { path: '/gradjanin', component: GradjaninPage },
        { path: '/zalba-na-cutanje', component: ZalbaNaCutanje },
        { path: '/zalba-na-odluku', component: ZalbaNaOdluku },
        { path: '/search', component: RequestsSearchPage },
        { path: '/advance-search', component: RequestsAdvanceSearchPage },
        { path: '/search-complaint-res', component: ComplaintResolutionSearchPage },
        { path: '/advance-search-complaint-res', component: ComplaintResolutionAdvanceSearchPage },
         { path: '/complaint-res-list', component: ComplaintResolutionTablePage },
        { path: '/create-resolution', component: NewResolutionPage },
        { path: '/resolution-list', component: ResolutionTablePage },
    
        { path: '/', component: LoginPage },
         { path: '/resolution-search', component: ResolutionSearchPage },

    ]
})

var app = new Vue({
    router,
    el: '#app'
})