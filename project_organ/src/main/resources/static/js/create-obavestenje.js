const CreateObavestenje = Vue.component("create-obavestenje", {
    template: `
    <div>
        <div class="editor-box">
                <h2>Slanje obavestenja</h2>
                <div id="editor"></div>
                <button v-on:click="submit()">Posalji obavestenje</button>
        </div>
    </div>
    `,
    methods: {
        submit() {
            var xml=Xonomy.harvest();
            console.log(xml)
            axios.post("/api/notification", xml, {headers: {'Content-Type': 'application/xml'}}).then(
            response => {
                alert('Obavestenje uspesno primljeno. Dobicete odgovor od poverenika putem elektronske poste.');
            },
            error => {
                alert('Doslo je do greske prilikom obavestenja.');
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
                "obavestenje": {
                    hasText: false
                },
                "zakon": {
                    hasText: false,
                    attributes: {
                        "naziv": {
                            asker: Xonomy.askString
                        }
                    }
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
                    hasText: false,
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
                "teloObavestenja" : {
                    hasText: false,
                    attributes : {
                        "godina" : {
                            asker : Xonomy.askString
                        },
                        "dan" : {
                            asker : Xonomy.askString
                        },
                        "sati" : {
                            asker : Xonomy.askString
                        },
                        "pocetniSati" : {
                            asker : Xonomy.askString
                        },
                        "zavrsniSati" : {
                            asker : Xonomy.askString
                        },
                        "kancelarija" : {
                            asker : Xonomy.askString
                        },
                        "suma" : {
                            asker : Xonomy.askString
                        }
                    }
                },
                "opcija" : {
                    attributes: {
                        "cekiran": {
                            asker: Xonomy.askPicklist,
                            askerParameter: [
                                {value: "true"},
                                {value: "false"}
                            ]
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
        `<obavestenje>
            <organ naziv=''><adresa ulica='' broj='' mesto='' postanskiBroj='' drzava='' /></organ>
            <zakon naziv=''></zakon>
            <teloObavestenja godina ='' dan='' sati='' pocetniSati='' zavrsniSati='' kancelarija='' suma='' >
                <adresa ulica='' broj='' mesto='' postanskiBroj='' drzava='' />
            </teloObavestenja>
            <opcija cekiran='false' tekst='архиви'/>
            <opcija cekiran='false' tekst='именованом'/>
        </obavestenje>`;
        var editor = document.getElementById("editor");
        Xonomy.render(xml, editor, docSpec);
    }
})