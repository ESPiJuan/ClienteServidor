var express = require("express");
var bodyParser = require('body-parser')
var app = express();

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));




const accerderDatosClientes = (req, res) => {
    res.send({
        Nombre: "Pedro",
        Deuda: "150€"
    })

};
const accerderDatosTodosClientes = (req, res) => {
    res.json(
        [{
            Nombre: "Pedro",
            Deuda: "150€"
        },
        {
            Nombre: "alfonso",
            Deuda: "50€"
        }]
    )

};
var arrayClientes = [{
    Nombre: "Pedro",
    Deuda: "150€"
},
{
    Nombre: "alfonso",
    Deuda: "50€"
},{
    Nombre: "juan",
    Deuda: "50€"
}];
var nombreCliente;

const accerderDatosTodosClientesFiltrados = (req, res) => {
    nombreCliente = req.body.Nombre;
    
    
        
        
        var found = false;
        for(var i = 0; i < arrayClientes.length; i++) {
            if (arrayClientes[i].Nombre.toUpperCase() == nombreCliente.toUpperCase()) {
                found = true;
                break;
            }
        }
        if (found == true){
            res.send("El Cliente "+nombreCliente+" existe")
        }else{
            res.send("No existe ese usuario")
        }

};

app.post('/crearCliente', (req, res) => {
	var data = req.body.data; 
	console.log(data);

	res.status(200).json({
		message: "JSON Data received successfully"
	});

app.get("/datoscliente", accerderDatosClientes);

app.get("/datosclientes", accerderDatosTodosClientes);

app.get("/datosclientefiltro", accerderDatosTodosClientesFiltrados);






var port = 40;
app.listen(port, () => {
    console.log("server online in this port" + port);
});