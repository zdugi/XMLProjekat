const ZalbaNaOdluku = Vue.component("zalba-na-odluku", {
    template: `
    <div>
        <div class="editor-box">
                <h2>Slanje zalbe na odluku</h2>
                <div id="editor"></div>
                <button v-on:click="submit()">Posalji zalbu na odluku</button>
        </div>
    </div>
    `,
    methods: {
        submit() {
            var xml=Xonomy.harvest();
            console.log(xml)
            axios.post("/api/complaint/resolution", xml, {headers: {'Content-Type': 'application/xml'}}).then(response => {
            alert('Zalba NA ODLUKU uspesno podnesena. Dobicete odgovor od poverenika putem elektronske poste.')})
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
                "zalilac": {
                    hasText: false,
                    attributes: {
                        "ime": {
                         asker: Xonomy.askString
                       },
                       "prezime": {
                          asker: Xonomy.askString
                       }
                   }
                },
                "organZalilac": {
                    hasText: false,
                    attributes: {
                        "naziv": {
                            asker: Xonomy.askString
                        }
                    }
                },
                "organNaKogaSeZali": {
                    hasText: false,
                    attributes: {
                        "naziv": {
                            asker: Xonomy.askString
                        }
                    }
                },
                "brojResenja":{
                    hasText: false,
                    attributes: {
                        "broj": {
                            asker: Xonomy.askString
                        },
                        "godina":{
                            asker: Xonomy.askString
                        }
                    }
                },
                "datumPodnosenja": {
                    attributes: {
                        "datumPodnosenjaA": {
                            asker: Xonomy.askString
                        }
                    }
                },
                "opisZalbe": {
                    hasText: true,
                    asker: Xonomy.askString
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
          `<zalbaNaOdluku>
               <zalilac ime="" prezime=""></zalilac>
               <organZalilac naziv=''>
                   <adresa ulica='' broj='' mesto='' postanskiBroj='' drzava='' />
               </organZalilac>
                <organNaKogaSeZali naziv=''>
                   <adresa ulica='' broj='' mesto='' postanskiBroj='' drzava='' />
               </organNaKogaSeZali>
               <brojResenja broj="" godina=""></brojResenja>
               <datumPodnosenja datumPodnosenjaA=''></datumPodnosenja>
               <opisZalbe></opisZalbe>
               <dodatneInformacije mesto="" datum="">
                   <trazilac kontakt="">
                       <osoba ime="" prezime=""></osoba>
                       <adresa ulica='' broj='' mesto='' postanskiBroj='' drzava='' />
                   </trazilac>
               </dodatneInformacije>

               <mestoPodnosenja naziv='' />
           </zalbaNaOdluku>
                  `
        var editor = document.getElementById("editor");
        Xonomy.setMode("laic");
        Xonomy.render(xml, editor, docSpec);
        Xonomy.setMode("laic");
    }
})