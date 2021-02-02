Vue.component('menu-component', {
    template: `
    <ul>
        <li><router-link to="/">Listanje</router-link></li>
        <li><router-link to="/search">Pretraga zalbi</router-link></li>
        <li><router-link to="/advance-search">Napredna pretraga zalbi</router-link></li>
        <li><router-link to="/create">Dodavanje111</router-link></li>
        <li><a href="#logout">Odjavi se</a></li>
        <li><router-link to="/zalba-na-cutanje">Zalba</router-link></li>
         <li><router-link to='/complaint-res-list'>Listanje zalbi na odluku</router-link></li>
        <li><router-link to="/zalba-na-odluku">Zalba na odluku</router-link></li>
         <li><router-link to="/search-complaint-res">Pretraga zalbi na odluku</router-link></li>
         <li><router-link to="/advance-search-complaint-res">Napredna pretraga zalbi na odluku</router-link></li>
         <li><router-link to='/create-resolution'>Kreiraj resenje</router-link></li>
         <li><router-link to='/resolution-list'>Listaj resenja</router-link></li>
    </ul>
    `
})