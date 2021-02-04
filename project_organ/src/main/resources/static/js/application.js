const router = new VueRouter({
    mode: 'hash',
    routes: [
        { path: '/citizen', component: CitizenPage,
          children: [
            {
                path: '',
                redirect: 'submit'
            },
            {
                path: 'submit',
                component: SubmitRequestPage
            },
            {
                path: 'list',
                component: RequestsTablePage
             }
          ]
        },
        { path: '/official', component: OfficialPage,
          children: [
            {
                path: '',
                redirect: 'requests-list'
            },
            {
                path: 'requests-list',
                component: RequestsTablePage
            },
            {
                path: 'requests-search',
                component: RequestsSearchPage
            },
            {
                path: 'requests-advance-search',
                component: RequestsAdvanceSearchPage
            },
            {
                path: 'reports',
                component:  ListCreateReportPage
            },
            {
                path: 'reports-advance-search',
                component: ReportsAdvanceSearchPage
            },
            {
                path: 'reports-search',
                component: ReportsSearchPage
            },
            {
                path: 'create-response',
                component: CreateObavestenje
            }
          ]
        },
        { path: '/login', component: LoginPage },
        { path: '/register', component: RegisterPage },
        { path: '/', redirect: '/citizen'}
    ]
});

var app = new Vue({
    router,
    el: '#app',
    beforeCreate() {
        var user = localStorage.getItem('currentUser');

        if (user) {
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + JSON.parse(user).token;
            console.log('token loaded');
        }
    }
})