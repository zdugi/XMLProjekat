const ReportsSearchPage = Vue.component("reports-search-page-component", {
    data() {
        return {
            reports: [],
            query: ''
        }
    },
    template: `
    <div>
           <div class="query-box">
               <input v-model="query" type="text" placeholder="Enter query..">
               <button v-on:click="submitQuery">Search</button>
           </div>
            <table class="display-table" v-if="reports.length > 0">
                <tr>
                    <th>Sifra izvestaja</th>
                    <th colspan="2" class="text-center">Preuzimanje dokumenta</th>
                    <th colspan="2" class="text-center">Preuzimanje metapodataka</th>
                </tr>
                <tr v-for="item in reports">
                    <td>{{ item }}</td>
                    <td><a v-bind:href="'/api/reports/xhtml/' + item" target="_blank">XHTML</a></td>
                    <td><a v-bind:href="'/api/reports/pdf/' + item" target="_blank">PDF</a></td>
                    <td><a v-bind:href="'/api/reports/rdf/' + item" target="_blank">RDF</a></td>
                    <td><a v-bind:href="'/api/reports/json/' + item" target="_blank">JSON</a></td>
                </tr>
            </table>
        </div>
    `,
    methods : {
        submitQuery() {
            var self = this;
            var token = JSON.parse(localStorage.getItem('currentUser')).token;
            axios.get("/api/reports/simple-search?query=" + this.query, {headers: {'Content-Type': 'application/xml' ,'Authorization' : 'Bearer ' + token}}).then(
                            response => {
                                self.reports = [];
                                xmlDoc = $.parseXML(response.data);
                                $(xmlDoc).find('resource').each(function(){
                                     self.reports.push($(this).text());
                                });
                            },
                            error => {
                                alert('Query nije validan.');
                            });
        }
    }
})