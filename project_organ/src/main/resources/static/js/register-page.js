const RegisterPage = Vue.component('register-page', {
    data() {
        return {
            ime: '',
            prezime: '',
            ulica: '',
            broj: 1,
            postanskiBroj: 1,
            mesto: '',
            drzava: '',
            email: '',
            sifra: '',
            uloga: 'citizen',
            kontakt: ''
        }
    },
    template: `
    <div class="registration-box">
        <h2>Super Registracija</h2>
        <form method="post" @submit="checkForm">
            <input v-model="ime" minlength="3" type="text" name="firstname" placeholder="Ime" required/>
            <input v-model="prezime" minlength="3" type="text" name="surname" placeholder="Prezime" required/>
            <input v-model="ulica" minlength="3" type="text" name="ulica" placeholder="Ulica" required/>
            <input v-model="broj" min="1" type="number" name="broj" placeholder="Broj" required/>
            <input v-model="postanskiBroj" min="1" type="number" name="postanskiBroj" placeholder="Postanski broj" required/>
            <input v-model="mesto" minlength="3" type="text" name="mesto" placeholder="Mesto" required/>
            <input v-model="drzava" minlength="3" type="text" name="drzava" placeholder="Drzava" required/>
            <input v-model="email" minlength="3" type="email" name="email" placeholder="Email" required/>
            <input v-model="sifra" minlength="3" type="password" name="password" placeholder="Sifra" required/>
            <input v-model="kontakt" minlength="3" type="text" name="kontakt" placeholder="Kontakt" required/>
            <select v-model="uloga" required>
                <option value="citizen">Gradjanin</option>
                <option value="official">Organ vlasti</option>
            </select>
            <button style="width: 100%;">Registruj se</button>
        </form>
    </div>
    `,
    methods: {
        checkForm(e) {
            var xmlBody = '<korisnik ' +
                            'ime="' + this.ime + '" ' +
                            'prezime="' + this.prezime + '" ' +
                            'ulica="' + this.ulica + '" ' +
                            'broj="' + this.broj + '" ' +
                            'postanskiBroj="' + this.postanskiBroj + '" ' +
                            'mesto="' + this.mesto + '" ' +
                            'drzava="' + this.drzava + '" ' +
                            'email="' + this.email + '" ' +
                            'password="' + this.sifra + '" ' +
                            'role="' + this.uloga + '" ' +
                            'kontakt="' + this.kontakt + '" ' +
                          '/>';

            console.log('poslato', xmlBody);

            axios.post('/api/auth/register', xmlBody, { headers: {'Content-Type': 'application/xml'} }).then(
            response => {
                this.$router.push({ path: '/login' });
            },
            error => {
                alert('Doslo je do greske prilikom registracije. Pokusajte ponovo.')
            });

            e.preventDefault();
        }
    }
});