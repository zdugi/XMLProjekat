const RequestsAdvanceSearchPage = Vue.component("requests-advance-search-page-component", {
    data () {
        return {
            complaints: [],
            datum: '',
            organ: '',
            mesto: '',
            drzava: '',
            trazilac: ''
        }
    },
    template: `
    <div>
               <div class="query-box">
                   <input v-model="datum" type="text" placeholder="Datum podnošenja...">
                   <input v-model="organ" type="text" placeholder="Organ kom se podnosi...">
                   <input v-model="mesto" type="text" placeholder="Mesto organa...">
                   <input v-model="drzava" type="text" placeholder="Država organa...">
                   <input v-model="trazilac" type="text" placeholder="Tražilac..">
                   <span style="font-size:12px">*U polja se unose regularni izrazi.</span>
                   <button v-on:click="submitQuery">Search</button>
               </div>
                <table class="display-table" v-if="complaints.length > 0">
                    <tr>
                        <th>Sifra zahteva</th>
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
    methods: {
        submitQuery() {
            if (!this.datum.trim() &&
            !this.organ.trim() &&
            !this.mesto.trim() &&
            !this.drzava.trim() &&
            !this.trazilac.trim())
                alert('Bar jedno polje mora biti popunjeno.')

            var xmlBody = "<request>" +
                               "<submissionDateRegex>" + this.datum + "</submissionDateRegex>" +
                               "<authorityRegex>" + this.organ + "</authorityRegex>" +
                               "<placeRegex>" + this.mesto + "</placeRegex>" +
                               "<stateRegex>" + this.drzava + "</stateRegex>" +
                               "<applicantRegex>" + this.trazilac + "</applicantRegex>" +
                           "</request>";

            var self = this;

            axios.post('/api/complaint/advance-search', xmlBody, { headers: {'Content-Type': 'application/xml'} }).then(
                response => {
                    xmlDoc = $.parseXML(response.data);
                    results = $(xmlDoc).find('result');

                    $(results).each(function(){
                         requestUri = $(this).find('[name="subject"]').find('uri').text();
                         self.complaints.push(requestUri.substring(requestUri.lastIndexOf("/") + 1))
                    });
                }
            )
        }
    },
    mounted() {
        var resp = "";
    }
})