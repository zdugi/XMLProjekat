const HomePage = Vue.component("home-page", {
    template: `
    <div>
        <div class="editor-box">
                <h2>Slanje zahteva za pristup informacijama od javnog znacaja</h2>
                <div id="editor"></div>
                <button v-on:click="submit()">Posalji zahtev</button>
        </div>
    </div>
    `,
    methods: {
        submit() {
            var xml=Xonomy.harvest();
            console.log(xml)
            axios.post("/api/request", xml, {headers: {'Content-Type': 'application/xml'}}).then(response => {
            alert('Zahtev uspesno primljen. Dobicete odgovor od poverenika putem elektronske poste.')})
        }
    },
    data() {
        return {
            message: 'Hello World!'
        }
    },
    mounted() {
        var docSpec = {
            onchange: function () {
                console.log("I been changed now!")
            },
            validate: function (obj) {
                console.log("I be validatin' now!")
            },
            elements: {
                "zahtev": {
                    hasText: false
                },
                "organ": {
                    hasText: false,
                    attributes: {
                        "naziv": {
                            asker: Xonomy.askString
                        }
                    }
                },
                "adresa": {
                    attributes: {
                        "ulica": {
                            asker: Xonomy.askString
                        },
                        "broj": {
                            asker: Xonomy.askString
                        },
                        "postanskiBroj": {
                            asker: Xonomy.askString
                         },
                         "mesto": {
                            asker: Xonomy.askString
                         },
                         "drzava": {
                            asker: Xonomy.askString
                         }
                    }
                },
                "opcija": {
                    attributes: {
                        "cekiran": {
                            asker: Xonomy.askPicklist,
                            askerParameter: [
                                {value: "true"},
                                {value: "false"}
                            ]
                        }
                    }
                },
                "opis": {
                    attributes: {
                        "tekst": {
                            asker: Xonomy.askLongString
                        }
                    }
                },
                "mestoPodnosenja": {
                    attributes: {
                        "naziv": {
                            asker: Xonomy.askString
                        }
                    }
                }
            }
        };

        var xml2 =
        `
        <zahtev_za_infomacije></zahtev_za_infomacije>
        `

        var xml =
        `<zahtev><organ naziv=''><adresa ulica='' broj='' mesto='' postanskiBroj='' drzava='' /></organ><zahtevam><opcija cekiran='true' tekst='обавештење да ли поседује тражену информацију;'/><opcija cekiran='false' tekst='увид у документ који садржи тражену информацију;'/><opcija cekiran='false' tekst='копију документа који садржи тражену информацију;'/><opcija cekiran='false' tekst='достављање копије документа који садржи тражену информацију'><dostava cekiran='false' tekst='поштом'/><dostava cekiran='false' tekst='електронском поштом'/><dostava cekiran='false' tekst='⁫факсом'/><dostava cekiran='false' tekst='на други начин' dodatno=''/></opcija><opis tekst='ovde ide opis informacije...'/></zahtevam><mestoPodnosenja naziv='' /></zahtev>`;
        var editor = document.getElementById("editor");
        Xonomy.render(xml, editor, docSpec);
    }
})