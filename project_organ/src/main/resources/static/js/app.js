const router = new VueRouter({
    mode: 'hash',
    routes: [
        { path: '/create', component: HomePage },
        { path: '/login', component: LoginPage },
        { path: '/', component: SearchPage }
    ]
})

var app = new Vue({
    router,
    el: '#app'
})