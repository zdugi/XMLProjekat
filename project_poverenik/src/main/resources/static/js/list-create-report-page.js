const ListCreateReportPage = Vue.component('list-create-report-page-component', {
    data() {
        return {
            reports: [],
            disableButton: false
        }
    },
    template: `
    <div class="div-klasa">
        <div class="display-table">

        <table>
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
    </div>
    `,
    methods: {

        getAll() {
        var token = JSON.parse(localStorage.getItem('currentUser')).token;
            axios.get("/api/reports", {headers: {'Content-Type': 'application/xml','Authorization' : 'Bearer ' + token}}).then(
                        response => {
                            var xmlDoc = $.parseXML(response.data);
                            var self = this;
                            $(xmlDoc).find('complaint').each(function(){
                                self.reports.push($(this).text());
                            });
                        },
                        error => {
                            alert('Ne postoje instance izveštaja u bazi.');
                        });
        }
    },
    mounted() {
        this.getAll();
    }
})