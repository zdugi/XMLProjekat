Vue.component('menu-component', {
    template: `
    <ul>
        <li><router-link to="/">Listanje</router-link></li>
        <li><router-link to="/create">Dodavanje</router-link></li>
        <li><a href="#logout">Odjavi se</a></li>
        <li><router-link to="/zalba-na-cutanje">Zalba</router-link></li>
    </ul>
    `
})