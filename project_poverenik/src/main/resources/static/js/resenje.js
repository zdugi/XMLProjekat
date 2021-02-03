NewResolutionPage = Vue.component("resenje", {
                        template: `
                        <div>
                            <div class="editor-box">
                                    <h2>Slanje resenja</h2>
                                    <div id="editor"></div>
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
                                    "id_zalbe": {
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

                            var xml =
                              `<resenje>
                                  <id_zalbe></id_zalbe>
                                  <resenje_ukratko></resenje_ukratko>
                                  <obrazlozenje></obrazlozenje>
                                  <prihvacena></prihvacena>
                               </resenje>
                                      `
                            var editor = document.getElementById("editor");

                            Xonomy.render(xml, editor, docSpec);

                        }
                    })