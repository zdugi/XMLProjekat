const OfficialPage = Vue.component('official-page', {
    template: `
    <div>
        <ul class="menu">
            <li><router-link to="/official/requests-list">Listanje zahteva</router-link></li>
            <li><router-link to="/official/requests-search">Pretraga zahteva</router-link></li>
            <li><router-link to="/official/requests-advance-search">Napredna pretraga zahteva</router-link></li>
            <li>/</li>
            <li><router-link to="/official/reports">Izvestaji</router-link></li>
            <li><router-link to="/official/reports-search">Pretraga izvestaja</router-link></li>
            <li><router-link to="/official/reports-advance-search">Napredna pretraga izvestaja</router-link></li>
            <li>/</li>
            <li><router-link to="/official/create-response">Dodaj obavestenje</router-link></li>
            <li>/<li>
            <li><a href="#logout">Odjavi se</a></li>
        </ul>
        <router-view></router-view>
    </div>
    `,
    methods: {
            logout() {
                localStorage.removeItem('currentUser');
                axios.defaults.headers.common['Authorization'] = '';
                this.$router.push({ path: '/login' });
            }
        },
    mounted() {
            if (!localStorage.getItem('currentUser'))
                this.$router.push({ path: '/login' });

            var user = JSON.parse(localStorage.getItem('currentUser'));

            if (user.role == 'ROLE_CITIZEN')
                this.$router.push({ path: '/citizen' });
    }
});