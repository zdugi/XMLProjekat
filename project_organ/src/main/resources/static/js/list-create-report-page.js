const ListCreateReportPage = Vue.component('list-create-report-page-component', {
    data() {
        return {
            reports: [],
            disableButton: false
        }
    },
    template: `
    <div>
        <div class="display-table">
            <button v-on:click="generate" :disabled="disableButton">
                <i v-if="disableButton" class="fa fa-circle-o-notch fa-spin"></i> Izgenerisi izvestaj
            </button>

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
        generate() {
            this.disableButton = true;
            axios.post('/api/reports/generate', {headers: {'Content-Type': 'application/xml'}}).then(
            response => {
                this.disableButton = false;
                var xmlDoc = $.parseXML(response.data);
                this.reports.unshift($(xmlDoc).find('Response').text())
                alert('Izvestaj je uspesno izgenerisan.');
            })
        },
        getAll() {
            axios.get("/api/reports", {headers: {'Content-Type': 'application/xml'}}).then(
                        response => {
                            var xmlDoc = $.parseXML(response.data);
                            var self = this;
                            $(xmlDoc).find('resource').each(function(){
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