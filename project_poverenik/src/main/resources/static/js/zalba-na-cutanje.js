const ZalbaNaCutanje = Vue.component("zalba-na-cutanje", {
    template: `
    <div>
        <div class="editor-box">
                <h2>Slanje zalbe na cutanje</h2>
                <div id="editor"></div>
                <button v-on:click="submit()">Posalji zalbu na cutanje</button>
        </div>
    </div>
    `,
    methods: {
        submit() {
            var xml=Xonomy.harvest();
            console.log(xml)
            axios.post("/api/complaint", xml, {headers: {'Content-Type': 'application/xml'}}).then(response => {
            alert('Zalba uspesno podnesena. Dobicete odgovor od poverenika putem elektronske poste.')})
        }
    },
    data() {
        return {
            message: 'Hello World!!!'
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

                "organ": {
                    hasText: false,
                    attributes: {
                        "naziv": {
                            asker: Xonomy.askString
                        }
                    }
                },

                "podaciOZahtevuIInformacijama": {
                    hasText: false
                },

                "datumPodnosenja": {
                    attributes: {
                        "datumPodnosenjaA": {
                            asker: Xonomy.askString
                        }
                    }
                },
                "podaciOZahtevuIInformacijama": {
                },
                "dodatneInformacije": {
                    attributes: {
                        "datum": {
                            asker: Xonomy.askString
                        },
                        "mesto": {
                            asker: Xonomy.askString
                        }
                    }
                },
                "trazilac": {
                    attributes: {
                          "kontakt": {
                      asker: Xonomy.askString
                    }}

                },
                "osoba": {
                    attributes: {
                        "ime": {
                         asker: Xonomy.askString
                       },
                       "prezime": {
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
          `<zalbaNaCutanje><organ naziv=''><adresa ulica='' broj='' mesto='' postanskiBroj='' drzava='' /></organ><datumPodnosenja datumPodnosenjaA=''></datumPodnosenja><podaciOZahtevuIInformacijama></podaciOZahtevuIInformacijama>
               <dodatneInformacije mesto="" datum=""><trazilac kontakt=""><osoba ime="" prezime=""></osoba></trazilac></dodatneInformacije>
               <opcije><opcija cekiran='true' tekst='није поступио;'/><opcija cekiran='false' tekst='није поступио у целости;'/><opcija cekiran='false' tekst= "није поступио у законском року"/></opcije>
               <mestoPodnosenja naziv='' />
           </zalbaNaCutanje>
                  `
        var editor = document.getElementById("editor");
        Xonomy.setMode("laic");
        Xonomy.render(xml, editor, docSpec);
        Xonomy.setMode("laic");
    }
})