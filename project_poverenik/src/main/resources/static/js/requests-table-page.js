const RequestsTablePage = Vue.component("requests-table-page-component", {
    data () {
        return {
            complaints: [],
            currentRole: JSON.parse(localStorage.getItem('currentUser')).roles
        }
    },
    template: `
    <div>
        <table class="display-table">
            <tr>
                <th>Sifra žalbi</th>
                <th colspan="2" class="text-center">Preuzimanje dokumenta</th>
                <th colspan="2" class="text-center">Preuzimanje metapodataka</th>
            </tr>
            <h1 v-if='currentRole == "ROLE_POVERENIK"'>ulogovan je poverenik</h1>
            <tr v-for="item in complaints">
                <td>{{ item }}</td>
                <td><a v-bind:href="'api/complaint/xhtml/' + item" target="_blank">XHTML</a></td>
                <td><a v-bind:href="'api/complaint/pdf/' + item" target="_blank">PDF</a></td>
                <td><a v-bind:href="'api/complaint/rdf/' + item" target="_blank">RDF</a></td>
                <td><a v-bind:href="'api/complaint/json/' + item" target="_blank">JSON</a></td>
                <td v-if='currentRole == "ROLE_POVERENIK"'><router-link :to="'/resolution/' + item">Sastavi resenje</router-link></td>

            </tr>
        </table>
    </div>
    `,
    mounted() {
    var currentRole = JSON.parse(localStorage.getItem('currentUser')).roles;
        var token = JSON.parse(localStorage.getItem('currentUser')).token;
        var self = this;
        if(currentRole == "ROLE_POVERENIK"){
    axios.get("/api/complaint", {headers: {'Content-Type': 'application/xml', 'Authorization' : 'Bearer ' + token}}).then(
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
                    alert('Doslo je do greske prilikom slanja zahteva.');
                });

    }else{
        axios.get("/api/complaint/user" , {headers: {'Content-Type': 'application/xml', 'Authorization' : 'Bearer ' + token}}).then(
                        response => {
                            //alert('Zahtev uspesno primljen. Dobicete odgovor od poverenika putem elektronske poste.');
                            xmlDoc = $.parseXML(response.data);
                            results = $(xmlDoc).find('result');
                            console.log(response.data)
                            $(results).each(function(){
                                requestUri = $(this).find('[name="subject"]').find('uri').text();
                                self.complaints.push(requestUri.substring(requestUri.lastIndexOf("/") + 1)+ ".xml")
                            });
                        },
                        error => {
                            alert('Doslo je do greske prilikom slanja zalbe na odluku.');
                        });

        }
        }
})