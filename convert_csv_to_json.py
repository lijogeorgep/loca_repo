import pandas as pd
import json

# Load the CSV file
csv_file = "localization.csv"
df = pd.read_csv(csv_file)

# Convert to JSON
localization_data = df.set_index('key').to_dict(orient='index')
with open('localization.json', 'w') as json_file:
    json.dump(localization_data, json_file)
