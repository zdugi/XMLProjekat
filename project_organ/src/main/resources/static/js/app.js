const router = new VueRouter({
    mode: 'hash',
    routes: [
        { path: '/create', component: HomePage },
        { path: '/login', component: LoginPage },
        { path: '/create-response/:zahtev', component: CreateObavestenje},
        { path: '/search', component: RequestsSearchPage },
        { path: '/reports-search', component: ReportsSearchPage },
        { path: '/advance-search', component: RequestsAdvanceSearchPage },
        { path: '/reports-advance-search', component: ReportsAdvanceSearchPage },
        { path: '/reports', component: ListCreateReportPage },
        { path: '/', component: RequestsTablePage },
        { path: '/list-obavestenje', component: RequestsTablePageObavestenje }
    ]
})

var app = new Vue({
    router,
    el: '#app'
})