import re
import numpy as np
from pprint import pprint


def get_table_row(line):
    data = [
        float(val)
        for val in line.replace(" ", "").split("|")
        if re.match(r"^(\-)?\d*\.\d+|\d+", val)
    ]
    return data


def get_error_percentage(real, estimate):
    return (estimate - real) / real


def get_speedup(time_1, time_2):
    return time_1 / time_2


def get_parallel_efficiency(time_1, time_2, number_of_cores):
    return time_1 / (number_of_cores * time_2)


def make_parallel_plot(values, core_counts, color="b"):
    figure, axes = plt.subplots()
    axes.plot(core_counts, values, color)
    axes.scatter(core_counts, values, c=color)
    for value, core_count in zip(values, core_count):
        axes.text(core_count, value, value)
    return axes


if __name__ == "__main__":
    pi_real = 3.141592

    assignment_directory = "./assignment_3/"
    report_file = "Report/report.md"
    data_files = ("n1_data.md", "n2_data.md", "n3_data.md")

    runtime_data, pi_data = data[(len(data) // 2) :], data[: (len(data) // 2)]
    serial_data, sp_pi_estimations, cc_pi_estimations = {}, {}, {}
    n = 0

    for row in pi_data:
        row.pop(0)
        cc_pi_estimations[2 ** n] = row[1::2]
        sp_pi_estimations[2 ** n] = row[0::2]
        n += 1

    serial_data["pi_estimation"] = [3.143586, 3.141867, 3.141596]
    serial_data["runtime"] = [2.0, 10.0, 94.0]
    serial_runtimes, sp_runtimes, cc_runtimes = {}, {}, {}
    n = 0

    for row in runtime_data:
        row.pop(0)
        cc_runtimes[2 ** n] = row[1::2]
        sp_runtimes[2 ** n] = row[0::2]
        n += 1

    print("\nError for serial data:\n")
    for estimation in serial_data["pi_estimation"]:
        error = get_error_percentage(pi_real, estimation)
        print(error)

    for core_count in sp_pi_estimations:
        print("\nError for core count " + str(core_count))
        for estimation in sp_pi_estimations[core_count]:
            error = get_error_percentage(pi_real, estimation)
            print(error)
    for core_count in cc_runtimes:
        n = 0
        print("\nEfficiency for core count " + str(core_count))
        for runtime in cc_runtimes[core_count]:
            # print(serial_data["runtime"][n])
            # print(runtime)
            # print(core_count)
            print(
                get_parallel_efficiency(serial_data["runtime"][n], runtime, core_count)
            )
            n += 1
    pprint(sp_runtimes)
