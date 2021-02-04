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
            var token = JSON.parse(localStorage.getItem('currentUser')).token;
            var xml=Xonomy.harvest();
            console.log(xml)
            axios.post("/api/complaint", xml, {headers: {'Content-Type': 'application/xml', 'Authorization' : 'Bearer ' + token}}).then(response => {
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
               "idZahteva": {
                    displayName: "ИД захтева",
                   hasText: true,
                   asker: Xonomy.askString
                },


                "podaciOZahtevuIInformacijama": {
                    displayName: "Подаци о захтеву и информацијама",
                    hasText: false,
                    attributes: {
                        "podaci": {
                            asker: Xonomy.askLongString
                        }
                    }

                },
               "opcija": {
                    displayName: "Разлог изјаве жалбе",
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
          `<zalbaNaCutanje><idZahteva></idZahteva><podaciOZahtevuIInformacijama podaci=''></podaciOZahtevuIInformacijama>
               <opcija cekiran='true' tekst='није поступио;'/><opcija cekiran='false' tekst='није поступио у целости;'/><opcija cekiran='false' tekst= "није поступио у законском року"/>
           </zalbaNaCutanje>
                  `
        var editor = document.getElementById("editor");

        Xonomy.render(xml, editor, docSpec);
    }
})