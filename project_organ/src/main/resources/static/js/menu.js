Vue.component('menu-component', {
    template: `
    <ul class="menu">
        <li><router-link to="/">Listanje zahteva</router-link></li>
        <li><router-link to="/create">Podnosenje zahteva</router-link></li>
        <li><a href="#logout">Odjavi se</a></li>
    </ul>
    `
})