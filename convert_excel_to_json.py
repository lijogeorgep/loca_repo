import pandas as pd
import json

# Load the Excel file
excel_file = "localization.xlsx"
df = pd.read_excel(excel_file)

# Convert to JSON
localization_data = df.set_index('key').to_dict(orient='index')
with open('localization.json', 'w') as json_file:
    json.dump(localization_data, json_file)
