NewResolutionPage = Vue.component("resenje", {
                        template: `
                        <div>
                            <div class="editor-box">
                                    <h2>Slanje resenja</h2>
                                    <div id="editor"></div>
                                    <p> Id zalbe: {{ idZalbe }}</p>
                                    <button v-on:click="submit()">Posalji resenje</button>
                            </div>
                        </div>
                        `,
                        methods: {
                            submit() {
                                var xml=Xonomy.harvest();
                                console.log(xml)
                                axios.post("/api/solution", xml, {headers: {'Content-Type': 'application/xml'}}).then(response => {
                                alert('Resenje uspesno kreirano.')})
                            }
                        },
                        data() {
                            let x = window.location.hash.split('/');

                            return {
                                //idZalbe: "idazlbe"
                                idZalbe: x[x.length - 1]
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
                                    "id_zalbe": {
                                        isReadOnly: true,
                                         hasText: true,
                                         asker: Xonomy.askString
                                    },
                                     "resenje_ukratko": {
                                         hasText: true,
                                         asker: Xonomy.askString
                                     },
                                    "obrazlozenje": {
                                         hasText: true,
                                         asker: Xonomy.askString
                                     },
                                     "prihvacena": {
                                        hasText: true,
                                        asker: Xonomy.askPicklist,
                                        askerParameter: ["прихваћена", "oдбијена"],
                                     }
                                }
                            };

                            let x = window.location.hash.split('/');
                            let id = x[x.length - 1];
                            id = id.substring(0, id.length-4)
                            console.log(id + "isddasdasdasadsads")
                            var xml =
                              `<resenje>
                                  <id_zalbe>${id}</id_zalbe>
                                  <resenje_ukratko></resenje_ukratko>
                                  <obrazlozenje></obrazlozenje>
                                  <prihvacena></prihvacena>
                               </resenje>
                                      `
                            var editor = document.getElementById("editor");

                            Xonomy.render(xml, editor, docSpec);

                        }
                    })