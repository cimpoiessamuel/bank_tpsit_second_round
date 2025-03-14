import sys
import matplotlib
import matplotlib.pyplot as plt
import pandas as pd

# remove the bottom func. bar
matplotlib.rcParams["toolbar"] = "None"

# read csv
df = pd.read_csv(sys.argv[1], sep=";")

# window size
plt.figure(figsize=(16, 10))

# 2 col -> inv. - 3 col -> account trend
if len(df.columns) == 2:
    plt.plot(df['x'], df['y'], marker='o', linestyle='-', color='b', label="Trend")
else:
    plt.plot(df['x'], df['y'], marker='o', linestyle='-', color='b', label="Balance")
    plt.plot(df['x'], df['z'], marker='o', linestyle='--', color='y', label="Wallet")

plt.xlabel("Time (day)")
plt.ylabel("Value (€)")
plt.title("Chart")

plt.legend()
plt.grid(True)

# minimum values of axis
plt.locator_params(axis='x', nbins=40)
plt.locator_params(axis='y', nbins=40)

plt.xticks(rotation=60)

plt.tight_layout()
plt.show()
