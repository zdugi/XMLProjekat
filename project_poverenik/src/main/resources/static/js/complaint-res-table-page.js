const ComplaintResolutionTablePage = Vue.component("complaint-res-table-page-component", {
    data () {
        return {
            complaints: []
        }
    },
    template: `
    <div>
        <table class="display-table">
            <tr>
                <th>Sifra žalbi na odluku</th>
                <th colspan="2" class="text-center">Preuzimanje dokumenta</th>
                <th colspan="2" class="text-center">Preuzimanje metapodataka</th>
            </tr>
            <tr v-for="item in complaints">
                <td>{{ item }}</td>
                <td><a v-bind:href="'api/complaint/resolution/xhtml/' + item" target="_blank">XHTML</a></td>
                <td><a v-bind:href="'api/complaint/resolution/pdf/' + item" target="_blank">PDF</a></td>
                <td><a v-bind:href="'api/complaint/resolution/rdf/' + item" target="_blank">RDF</a></td>
                <td><a v-bind:href="'api/complaint/resolution/json/' + item" target="_blank">JSON</a></td>
            </tr>
        </table>
    </div>
    `,
    mounted() {
    axios.get("/api/complaint/resolution", {headers: {'Content-Type': 'application/xml'}}).then(
                response => {
                    //alert('Zahtev uspesno primljen. Dobicete odgovor od poverenika putem elektronske poste.');
                    xmlDoc = $.parseXML(response.data);
                    console.log(xmlDoc);
                    var self = this;
                    $(xmlDoc).find('complaint').each(function(){
                         self.complaints.push($(this).text());
                    });
                },
                error => {
                    alert('Doslo je do greske prilikom slanja zalbe na odluku.');
                });
    }
})