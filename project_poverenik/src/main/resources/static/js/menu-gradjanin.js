Vue.component('menu-component-gradjanin', {
    template: `
    <ul>
        <li><router-link to="/requests-table-page">Listanje zalbi na ćutanje</router-link></li>
         <li><router-link to='/complaint-res-list'>Listanje zalbi na odluku</router-link></li>
         <li><router-link to='/resolution-list'>Listanje resenja</router-link></li>
        <li><router-link to="/zalba-na-cutanje">Kreiraj zalbu na ćutanje</router-link></li>
        <li><router-link to="/zalba-na-odluku">Kreiraj zalba na odluku</router-link></li>

         <li><a href="#logout">Odjavi se</a></li>
    </ul>
    `
})