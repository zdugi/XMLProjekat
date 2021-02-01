const router = new VueRouter({
    mode: 'hash',
    routes: [
        { path: '/create', component: HomePage },
        { path: '/login', component: LoginPage },
        { path: '/zalba-na-cutanje', component: ZalbaNaCutanje },
        { path: '/zalba-na-odluku', component: ZalbaNaOdluku },
        { path: '/', component: SearchPage }
    ]
})

var app = new Vue({
    router,
    el: '#app'
})