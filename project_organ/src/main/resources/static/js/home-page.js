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
            axios.post("/api/requests", xml, {headers: {'Content-Type': 'application/xml'}}).then(
            response => {
                alert('Zahtev uspesno primljen. Dobicete odgovor od poverenika putem elektronske poste.');
            },
            error => {
                alert('Doslo je do greske prilikom slanja zahteva.');
            });
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
                },
                "dostava": {
                    attributes: {
                        "cekiran": {
                            asker: Xonomy.askPicklist,
                            askerParameter: [
                            {value: "true"},
                            {value: "false"}
                            ]
                        },
                        "dodatno": {
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
        `<zahtev><organ naziv=''><adresa ulica='' broj='' mesto='' postanskiBroj='' drzava='' /></organ><zahtevam><opcija cekiran='true' tekst='obaveštenje da li poseduje traženu informaciju;'/><opcija cekiran='false' tekst='uvid u dokument koji sadrži traženu informaciju;'/><opcija cekiran='false' tekst='kopiju dokumenta koji sadrži traženu informaciju;'/><opcija cekiran='false' tekst='dostavljanje kopije dokumenta koji sadrži traženu informaciju'><dostava cekiran='false' tekst='poštom'/><dostava cekiran='false' tekst='elektronskom poštom'/><dostava cekiran='false' tekst='faksom'/><dostava cekiran='false' tekst='na drugi način:' dodatno=''/></opcija><opis tekst='Ovde unesite na koje informacije se zahtev odnosi....'/></zahtevam><mestoPodnosenja naziv='' /></zahtev>`;
        var editor = document.getElementById("editor");
        Xonomy.render(xml, editor, docSpec);
    }
})