const CitizenPage = Vue.component('citizen-page', {
    template: `
    <div>
        <ul class="menu">
            <li><router-link to="/citizen/list">Listanje zahteva</router-link></li>
            <li><router-link to="/citizen/submit">Podnosenje zahteva</router-link></li>
            <li>/</li>
            <li><a href="#logout">Odjavi se</a></li>
        </ul>
        <router-view></router-view>
    </div>
    `
});