
Vue.component('menu-component', {
    template: `
    <ul>
        <li><router-link to="/requests-table-page">Listanje zalbi na ćutanje</router-link></li>
         <li><router-link to='/complaint-res-list'>Listanje zalbi na odluku</router-link></li>
         <li><router-link to='/resolution-list'>Listanje resenja</router-link></li>
         <li><router-link to='/create-resolution'>Kreiraj resenje</router-link></li>
        <li><router-link to="/search">Pretraga zalbi na ćutanje</router-link></li>
         <li><router-link to="/search-complaint-res">Pretraga zalbi na odluku</router-link></li>
        <li><router-link to="/advance-search">Napredna pretraga zalbi na ćutanje</router-link></li>
        <li><router-link to="/advance-search-complaint-res">Napredna pretraga zalbi na odluku</router-link></li>

         <li><a href="#logout">Odjavi se</a></li>
    </ul>
    `
})