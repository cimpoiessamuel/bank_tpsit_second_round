import sys
import matplotlib
import matplotlib.pyplot as plt
import pandas as pd

matplotlib.rcParams["toolbar"] = "None"

df = pd.read_csv(sys.argv[1], sep=";")

plt.figure(figsize=(16, 10))

plt.plot(df['x'], df['y'], linestyle='-', color='b', label="Trend")

plt.xlabel("Time (day)")
plt.ylabel("Value (€)")
plt.title("Chart")

plt.legend()
plt.grid(True)

plt.locator_params(axis='x', nbins=40)
plt.locator_params(axis='y', nbins=40)

plt.show()
