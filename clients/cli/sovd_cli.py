# SPDX-License-Identifier: Apache-2.0
import argparse, json
from urllib.request import urlopen

parser = argparse.ArgumentParser()
parser.add_argument('--host', default='http://localhost:8080')
args = parser.parse_args()

for path in ['/api/entities', '/api/entities/vehicle-001/faults']:
    url = args.host + path
    try:
        with urlopen(url, timeout=3) as r:
            print(path, '->', r.status, json.loads(r.read().decode('utf-8')))
    except Exception as e:
        print(path, '->', 'error:', e)
