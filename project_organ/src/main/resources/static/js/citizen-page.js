const CitizenPage = Vue.component('citizen-page', {
    template: `
    <div>
        <ul class="menu">
            <li><router-link to="/citizen/list">Listanje zahteva</router-link></li>
            <li><router-link to="/citizen/submit">Podnosenje zahteva</router-link></li>
            <li>/</li>
            <li><span style="color: #9b4dca;text-decoration: none;cursor: pointer;" v-on:click="logout()">Odjavi se</span></li>
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

        if (user.roles == 'ROLE_OFFICIAL')
            this.$router.push({ path: '/official' });
    }
});