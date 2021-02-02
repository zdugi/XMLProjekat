const ReportsAdvanceSearchPage = Vue.component("reports-advance-search-page-component", {
    data () {
        return {
            reports: [],
            ukupno: '',
            odbijeni: '',
        }
    },
    template: `
    <div>
               <div class="query-box">
                   <input v-model="ukupno" type="text" placeholder="Broj podnetih...">
                   <input v-model="odbijeni" type="text" placeholder="Broj odbijenih...">
                   <span style="font-size:12px">*U polja se unose regularni izrazi.</span>
                   <button v-on:click="submitQuery">Pretrazi</button>
               </div>
                <table class="display-table" v-if="reports.length > 0">
                    <tr>
                        <th>Sifra izvestaja</th>
                        <th colspan="2" class="text-center">Preuzimanje dokumenta</th>
                        <th colspan="2" class="text-center">Preuzimanje metapodataka</th>
                    </tr>
                    <tr v-for="item in reports">
                        <td>{{ item }}</td>
                        <td><a v-bind:href="'api/reports/xhtml/' + item" target="_blank">XHTML</a></td>
                        <td><a v-bind:href="'api/reports/pdf/' + item" target="_blank">PDF</a></td>
                        <td><a v-bind:href="'api/reports/rdf/' + item" target="_blank">RDF</a></td>
                        <td><a v-bind:href="'api/reports/json/' + item" target="_blank">JSON</a></td>
                    </tr>
                </table>
            </div>
    `,
    methods: {
        submitQuery() {
            if (!this.odbijeni.trim() &&
            !this.ukupno.trim())
                alert('Bar jedno polje mora biti popunjeno.')

            var xmlBody = "<request>" +
                               "<numberOfSubmittedRegex>" + this.ukupno + "</numberOfSubmittedRegex>" +
                               "<numberOfDeclinedRegex>" + this.odbijeni + "</numberOfDeclinedRegex>" +
                           "</request>";

            var self = this;

            axios.post('/api/reports/advance-search', xmlBody, { headers: {'Content-Type': 'application/xml'} }).then(
                response => {
                    xmlDoc = $.parseXML(response.data);
                    results = $(xmlDoc).find('result');

                    self.reports = [];

                    $(results).each(function(){
                         requestUri = $(this).find('[name="subject"]').find('uri').text();
                         self.reports.push(requestUri.substring(requestUri.lastIndexOf("/") + 1))
                    });
                }
            )
        }
    },
    mounted() {
        var resp = "";
    }
})