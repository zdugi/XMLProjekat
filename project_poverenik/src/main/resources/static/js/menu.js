
Vue.component('menu-component', {
    data: function() {
        return {
            user: localStorage.getItem("currentUser")
        }
    },
    template: `
    <div>
    <ul v-if="checkUser()==='ROLE_POVERENIK'">
        <li><router-link to="/requests-table-page">Listanje zalbi na cutanje</router-link></li>
         <li><router-link to='/complaint-res-list'>Listanje zalbi na odluku</router-link></li>
         <li><router-link to='/resolution-list'>Listanje resenja</router-link></li>
          <li><router-link to="/reports-list">Listanje izvestaja</router-link></li>
         <li><router-link to='/create-resolution'>Kreiraj resenje</router-link></li>
        <li><router-link to="/search">Pretraga zalbi na cutanje</router-link></li>
         <li><router-link to="/search-complaint-res">Pretraga zalbi na odluku</router-link></li>
         <li><router-link to='/resolution-search'>Pretraga resenja</router-link></li>
         <li><router-link to="/reports-search">Pretraga izvestaja</router-link></li>
        <li><router-link to="/advance-search">Napredna pretraga zalbi na cutanje</router-link></li>
        <li><router-link to="/advance-search-complaint-res">Napredna pretraga zalbi na odluku</router-link></li>
         <li><router-link to="/advance-search-resolution">Napredna pretraga resenja</router-link></li>
         <li><router-link to="/reports-advance-search">Napredna pretraga izvestaja</router-link></li>
        <li><router-link to="/messenger">Poruke</router-link></li>

         <li><span style="color: #9b4dca;text-decoration: none;cursor: pointer;" v-on:click="logout()">Odjavi se</span></li>

    </ul>
    <ul v-if="checkUser()==='ROLE_USER'">
            <li><router-link to="/requests-table-page">Listanje zalbi na cutanje</router-link></li>
             <li><router-link to='/complaint-res-list'>Listanje zalbi na odluku</router-link></li>
             <li><router-link to='/resolution-list'>Listanje resenja</router-link></li>
             li><router-link to="/search">Pretraga zalbi na cutanje</router-link></li>
             <li><router-link to="/search-complaint-res">Pretraga zalbi na odluku</router-link></li>
             li><router-link to="/advance-search">Napredna pretraga zalbi na cutanje</router-link></li>
            <li><router-link to="/advance-search-complaint-res">Napredna pretraga zalbi na odluku</router-link></li>
             <li><router-link to="/zalba-na-cutanje">Kreiraj zalbu na ćutanje</router-link></li>
             <li><router-link to="/zalba-na-odluku">Kreiraj zalbu na odluku</router-link></li>
             <li><span style="color: #9b4dca;text-decoration: none;cursor: pointer;" v-on:click="logout()">Odjavi se</span></li>

        </ul>
    </div>
    `,
         methods: {
                 logout() {
                     localStorage.removeItem('currentUser');
                     axios.defaults.headers.common['Authorization'] = '';
                     this.$router.push({ path: '/' });
                 },
                 checkUser(){
                    user = localStorage.getItem("currentUser");
                   if (!user){
                   return false;
                   }
                   console.log("uloga " + JSON.parse(localStorage.getItem('currentUser')).roles)
                   return JSON.parse(localStorage.getItem('currentUser')).roles;

                 }
             },
             mounted() {
                 console.log(localStorage.getItem("currentUser") + " korisnik");
                 /*if (!localStorage.getItem('currentUser')){
                     this.$router.push({ path: '/' });

                 }

                 var user = JSON.parse(localStorage.getItem('currentUser'));

                 if (user.roles == 'ROLE_USER')
                     this.$router.push({ path: '/gradjanin' });*/
             },watch: {

                $route (to, from){
                        console.log(to  + "  " + from + " pratim rutu");
                        console.log(to);
                        if (to.path === "/gradjanin"){
                            console.log("uso u if");
                            this.user = JSON.parse(localStorage.getItem("currentUser"));
                            this.$mount();
                            this.$forceUpdate();
                            this.$mount();
                            }

                        if (to.path === "/poverenik"){
                            console.log("uso u if");
                            this.user = JSON.parse(localStorage.getItem("currentUser"));
                            this.$mount();
                            this.$forceUpdate();
                            this.$mount();
                            }
                        if (to.path === "/"){
                            console.log("uso u if");
                            this.user = JSON.parse(localStorage.getItem("currentUser"));
                            this.$mount();
                            this.$forceUpdate();
                            this.$mount();
                            }
                    }

               }
})