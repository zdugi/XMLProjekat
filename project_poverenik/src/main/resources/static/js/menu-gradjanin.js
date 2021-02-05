Vue.component('menu-component-gradjanin', {
    template: `
    <ul>
        <li><router-link to="/requests-table-page">Listanje zalbi na ćutanje</router-link></li>
         <li><router-link to='/complaint-res-list'>Listanje zalbi na odluku</router-link></li>
         <li><router-link to='/resolution-list'>Listanje resenja</router-link></li>
        <li><router-link to="/zalba-na-cutanje">Kreiraj zalbu na ćutanje</router-link></li>
        <li><router-link to="/zalba-na-odluku">Kreiraj zalba na odluku</router-link></li>

         <li><span style="color: #9b4dca;text-decoration: none;cursor: pointer;" v-on:click="logout()">Odjavi se</span></li>
    </ul>
    `,
    methods: {
            logout() {
                localStorage.removeItem('currentUser');
                axios.defaults.headers.common['Authorization'] = '';
                this.$router.push({ path: '/' });
            }
        },
        mounted() {
            if (!localStorage.getItem('currentUser'))
                this.$router.push({ path: '/' });

            var user = JSON.parse(localStorage.getItem('currentUser'));

            if (user.roles == 'ROLE_POVERENIK')
                this.$router.push({ path: '/poverenik' });
        }
})