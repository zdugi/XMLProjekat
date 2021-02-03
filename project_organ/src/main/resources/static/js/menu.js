Vue.component('menu-component', {
    template: `
    <ul class="menu">
        <li><router-link to="/">Listanje zahteva</router-link></li>
        <li><router-link to="/create">Podnosenje zahteva</router-link></li>
        <li><router-link to="/search">Pretraga zahteva</router-link></li>
        <li><router-link to="/advance-search">Napredna pretraga zahteva</router-link></li>
        <li>/</li>
        <li><router-link to="/reports">Izvestaji</router-link></li>
        <li><router-link to="/reports-search">Pretraga izvestaja</router-link></li>
        <li><router-link to="/reports-advance-search">Napredna pretraga izvestaja</router-link></li>
        <li>/</li>
        <li><a href="#logout">Odjavi se</a></li>
        <li><router-link to="/create-obavestenje">Dodaj obavestenje</router-link></li>
        <li><router-link to="/list-obavestenje">Lista obavestenja</router-link></li>
    </ul>
    `
})