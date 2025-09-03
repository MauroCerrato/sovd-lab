from flask import Flask, jsonify

app = Flask(__name__)

@app.route('/vehicle-001/api/vin', methods=['GET'])
def get_vin():
    return jsonify({"vin": "VEHICLEOBDTEST01"})

@app.route('/vehicle-001/api/dtc', methods=['GET'])
def get_dtcs():
    return jsonify({
        "dtcs": [
            {"code": "P1A01", "description": "Powertrain fault - manufacturer specific"},
            {"code": "B2B02", "description": "Body fault - manufacturer specific"},
            {"code": "C3C03", "description": "Chassis fault - manufacturer specific"},
            {"code": "U4D04", "description": "Network fault - manufacturer specific"}
        ]
    })

@app.route('/vehicle-001/api/software-version', methods=['GET'])
def get_sw_version():
    return jsonify({"ecu_software_version": "3.2.105"})

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8080)
