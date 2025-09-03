import requests

BASE_URL = "http://localhost:8080/vehicle-001/api"

def fetch_vin():
    response = requests.get(f"{BASE_URL}/vin")
    print("VIN:", response.json()["vin"])

def fetch_dtcs():
    response = requests.get(f"{BASE_URL}/dtc")
    print("DTCs:")
    for dtc in response.json()["dtcs"]:
        print(f"  {dtc['code']}: {dtc['description']}")

def fetch_sw_version():
    response = requests.get(f"{BASE_URL}/software-version")
    print("ECU Software Version:", response.json()["ecu_software_version"])

if __name__ == "__main__":
    fetch_vin()
    fetch_dtcs()
    fetch_sw_version()
