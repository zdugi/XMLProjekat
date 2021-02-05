
Vue.component('menu-component', {
    template: `
    <ul>
        <li><router-link to="/requests-table-page">Listanje zalbi na cutanje</router-link></li>
         <li><router-link to='/complaint-res-list'>Listanje zalbi na odluku</router-link></li>
         <li><router-link to='/resolution-list'>Listanje resenja</router-link></li>
         <li><router-link to='/create-resolution'>Kreiraj resenje</router-link></li>
        <li><router-link to="/search">Pretraga zalbi na cutanje</router-link></li>
         <li><router-link to="/search-complaint-res">Pretraga zalbi na odluku</router-link></li>
         <li><router-link to='/resolution-search'>Pretraga resenja</router-link></li>
        <li><router-link to="/advance-search">Napredna pretraga zalbi na cutanje</router-link></li>
        <li><router-link to="/advance-search-complaint-res">Napredna pretraga zalbi na odluku</router-link></li>
         <li><router-link to="/advance-search-resolution">Napredna pretraga resenja</router-link></li>
        <li><router-link to="/messenger">Poruke</router-link></li>

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

                 if (user.roles == 'ROLE_USER')
                     this.$router.push({ path: '/gradjanin' });
             }
})