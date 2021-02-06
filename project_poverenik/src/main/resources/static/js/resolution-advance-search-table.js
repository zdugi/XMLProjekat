const ResolutionsAdvanceSearchPage = Vue.component("resolutions-advance-search-page-component", {
    data () {
        return {
            complaints: [],
            resenjeZa: '',
            doneseno: '',
            prihvacena: '',
            zalilac: '',
            upucujeSe: '',
            datumPodnosenjaZahteva: ''

        }
    },
    template: `
    <div class="div-klasa">
               <div class="query-box">
                <input v-model="resenjeZa" type="text" placeholder="ID zalbe...">
                <input v-model="doneseno" type="text" placeholder="Datum donosenja...">
                <input v-model="zalilac" type="text" placeholder="ID zalioca...">
                <input v-model="upucujeSe" type="text" placeholder="Organ kome se upucuje...">
                <input v-model="datumPodnosenjaZahteva" type="text" placeholder="Datum podnosenja zahteva...">

                   <span style="font-size:12px">*U polja se unose regularni izrazi.</span>
                   <button v-on:click="submitQuery">Search</button>
               </div>
                <table class="display-table" v-if="complaints.length > 0">
                    <tr>
                        <th>Sifra resenja</th>
                        <th colspan="2" class="text-center">Preuzimanje dokumenta</th>
                        <th colspan="2" class="text-center">Preuzimanje metapodataka</th>
                    </tr>
                    <tr v-for="item in complaints">
                        <td>{{ item }}</td>
                        <td><a v-bind:href="'api/solution/xhtml/' + item" target="_blank">XHTML</a></td>
                        <td><a v-bind:href="'api/solution/pdf/' + item" target="_blank">PDF</a></td>
                        <td><a v-bind:href="'api/solution/rdf/' + item" target="_blank">RDF</a></td>
                        <td><a v-bind:href="'api/solution/json/' + item" target="_blank">JSON</a></td>
                    </tr>
                </table>
            </div>
    `,
    methods: {
        submitQuery() {
            if (!this.resenjeZa.trim() &&
            !this.doneseno.trim() &&
            !this.prihvacena.trim() &&
            !this.zalilac.trim() &&
            !this.upucujeSe.trim() &&
            !this.datumPodnosenjaZahteva.trim())
                alert('Bar jedno polje mora biti popunjeno.')

            var xmlBody = "<request>" +
                               "<resenjeZa>" + this.resenjeZa + "</resenjeZa>" +
                               "<doneseno>" + this.doneseno + "</doneseno>" +
                               "<prihvacena>" + this.prihvacena + "</prihvacena>" +
                               "<zalilac>" + this.zalilac + "</zalilac>" +
                               "<upucujeSe>" + this.upucujeSe + "</upucujeSe>" +
                               "<datumPodnosenjaZahteva>" + this.datumPodnosenjaZahteva + "</datumPodnosenjaZahteva>" +
                           "</request>";
        console.log(xmlBody);
            var self = this;
            var token = JSON.parse(localStorage.getItem('currentUser')).token;
            axios.post('/api/solution/advance-search', xmlBody, { headers: {'Content-Type': 'application/xml','Authorization' : 'Bearer ' + token} }).then(
                response => {
                    self.complaints = [];
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