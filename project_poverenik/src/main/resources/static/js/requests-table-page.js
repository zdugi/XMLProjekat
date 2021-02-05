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
                <th v-if='currentRole == "ROLE_POVERENIK"' colspan="1" class="text-center">Status</th>
                <th v-if='currentRole == "ROLE_POVERENIK"' colspan="1" class="text-center">Sastavi resenje</th>
            </tr>
            <h1 v-if='currentRole == "ROLE_POVERENIK"'>ulogovan je poverenik</h1>
            <tr v-for="item in complaints">
                 <td>{{ item.id }}</td>
                 <td><a v-bind:href="'api/complaint/xhtml/' + item.id" target="_blank">XHTML</a></td>
                 <td><a v-bind:href="'api/complaint/pdf/' + item.id" target="_blank">PDF</a></td>
                 <td><a v-bind:href="'api/complaint/rdf/' + item.id" target="_blank">RDF</a></td>
                 <td><a v-bind:href="'api/complaint/json/' + item.id" target="_blank">JSON</a></td>
                 <td v-if='currentRole == "ROLE_POVERENIK"'>{{item.status}}</td>
                 <td v-if='currentRole == "ROLE_POVERENIK"'><router-link :to="'/resolution/' + item.id">Sastavi resenje</router-link></td><td v-if='currentRole == "ROLE_POVERENIK" && item.status == "нова"'><router-link :to="'/resolution/обавестиорган'">Obavesti organ vlasti</router-link></td>
                 <td v-if='currentRole == "ROLE_POVERENIK" && (item.status=="oдбијена" || item.status == "прихваћена")'><button disabled="true">Odbijena ili prihvacena</button></td>
                 <td v-if='currentRole == "ROLE_POVERENIK" && item.status == "чека се одговор органа власти"'><button disabled="true">Sastavi resenje</button></td>
                 <td v-if='currentRole == "ROLE_POVERENIK" && item.status == "чека решење"'><button>Sastavi resenje</button></td>

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
                    xmlDoc = $.parseXML(response.data);
                     console.log(xmlDoc);
                     var self = this;
                     xmlDoc = $.parseXML(response.data);
                     results = $(xmlDoc).find('complaint');
                     console.log(response.data);
                      $(xmlDoc).find('complaint').each(function(){
                     //$(results).each(function(){
                         console.log($(this).text());

                         let id = $(this).find('value').text();
                         let status =  $(this).find('status').text();
                         console.log("s" + status + "s");

                         //console.log($(this).textContent);
                         //console.log($(this).attributes["status"].value);
                         //requestUri = $(this).find('[name="subject"]').find('uri').text();
                         let c = {"id": id + ".xml",
                                  "status": status};
                         self.complaints.push(c);
                         //status = $(this).find('[name="status"]').find('literal').text();
                         //console.log("status " + status);
                     });              },
                error => {
                    alert('Doslo je do greske prilikom slanja zahteva.');
                });

    }else{
        axios.get("/api/complaint/user" , {headers: {'Content-Type': 'application/xml', 'Authorization' : 'Bearer ' + token}}).then(
                        response => {
                        xmlDoc = $.parseXML(response.data);
                        results = $(xmlDoc).find('result');
                        console.log(response.data)
                        $(results).each(function(){
                            requestUri = $(this).find('[name="subject"]').find('uri').text();
                            self.complaints.push({ "id": requestUri.substring(requestUri.lastIndexOf("/") + 1) + ".xml"})
                        });
                       },
                        error => {
                            alert('Doslo je do greske prilikom slanja zalbe na odluku.');
                        });

        }
        }
})