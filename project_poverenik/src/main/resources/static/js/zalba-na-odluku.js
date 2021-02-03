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
            var token = JSON.parse(localStorage.getItem('currentUser')).token;
            var xml=Xonomy.harvest();
            console.log(xml)
            axios.post("/api/complaint/resolution", xml, {headers: {'Content-Type': 'application/xml', 'Authorization' : 'Bearer ' + token}}).then(response => {
            alert('Zalba NA ODLUKU uspesno podnesena. Dobicete odgovor od poverenika putem elektronske poste.')})
        }
    },
    data() {
        return {
            koSeZali: 0
        }
    },
    mounted() {
        var docSpec = {
            onchange: function () {
                console.log("I been changed now!")
            },
            validate: function (obj) {
                console.log("I be validatin' now!")
                // ovde da ih postavim
                let element = document.getElementById("xonomy40");
                let el = document.querySelector("#xonomy40 > div > div");
                let chosen = el.getAttribute("data-value");
                if (chosen === "Osoba"){
                    let element123 = document.getElementById("xonomy79").style.display = "none";
                    let element123333 = document.getElementById("xonomy59").style.display = "display";
                    console.log(element123);
                    console.log(element123333);
                }
                else if (chosen === "Organ"){
                    let element456 = document.getElementById("xonomy59").style.display = "none";
                    let element12312 = document.getElementById("xonomy79").style.display = "display";
                }
                else {
                    //document.getElementById("xonomy79").style.display = "none";
                    //document.getElementById("xonomy59").style.display = "none";
                }
                console.log(element);
            },
            elements: {
                "idZahteva": {
                    hasText: true,
                    asker: Xonomy.askString
                },
                "podnosilac": {
                hasText: true,
                asker: Xonomy.askPicklist,
                askerParameter: ["Osoba", "Organ"],
                },
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
                    isInvisible: function(){
                        console.log(3);
                        //console.log(koSeZali + " ko se zali");
                        //return 3 < 4;
                        return false;
                    },
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

                "opisZalbe": {
                    hasText: true,
                    asker: Xonomy.askString
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
                }

             }
        };

        var xml2 =
        `
        <zahtev_za_infomacije></zahtev_za_infomacije>
        `

        var xml =
          `<zalbaNaOdluku>
                <idZahteva></idZahteva>
                <podnosilac></podnosilac>
               <zalilac ime="" prezime=""></zalilac>
               <organZalilac naziv=''>
                   <adresa ulica='' broj='' mesto='' postanskiBroj='' drzava='' />
               </organZalilac>

               <brojResenja broj="" godina=""></brojResenja>
                <opisZalbe></opisZalbe>
               </zalbaNaOdluku>
                  `
        var editor = document.getElementById("editor");

        Xonomy.render(xml, editor, docSpec);

        $(document).on("xonomy-click-element", function(event, jsMe){
        console.log("nesto se desilo");
        console.log(jsMe); });

        $(document).on("xonomy-click-attribute", function(event, jsMe){ console.log(jsMe); });


        }
})