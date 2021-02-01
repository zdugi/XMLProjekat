const RequestsSearchPage = Vue.component("requests-search-page-component", {
    data() {
        return {
            complaints: [],
            query: ''
        }
    },
    template: `
    <div>
           <div class="query-box">
               <input v-model="query" type="text" placeholder="Enter query..">
               <button v-on:click="submitQuery">Search</button>
           </div>
            <table class="display-table" v-if="complaints.length > 0">
                <tr>
                    <th>Sifra zalbi</th>
                    <th colspan="2" class="text-center">Preuzimanje dokumenta</th>
                    <th colspan="2" class="text-center">Preuzimanje metapodataka</th>
                </tr>
                <tr v-for="item in complaints">
                    <td>{{ item }}</td>
                    <td><a v-bind:href="'api/complaint/xhtml/' + item" target="_blank">XHTML</a></td>
                    <td><a v-bind:href="'api/complaint/pdf/' + item" target="_blank">PDF</a></td>
                    <td><a v-bind:href="'api/complaint/rdf/' + item" target="_blank">RDF</a></td>
                    <td><a v-bind:href="'api/complaint/json/' + item" target="_blank">JSON</a></td>
                </tr>
            </table>
        </div>
    `,
    methods : {
        submitQuery() {
            var self = this;
            axios.get("/api/complaint/simple-search?query=" + this.query, {headers: {'Content-Type': 'application/xml'}}).then(
                            response => {
                                //alert('Zahtev uspesno primljen. Dobicete odgovor od poverenika putem elektronske poste.');
                                self.complaints = [];
                                xmlDoc = $.parseXML(response.data);
                                $(xmlDoc).find('complaint').each(function(){
                                     self.complaints.push($(this).text());
                                });
                            },
                            error => {
                                alert('Query nije validan.');
                            });
        }
    }
})