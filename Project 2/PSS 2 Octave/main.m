% main.m
%% MAIN  Run the full pipeline and plot results

clear; clc;

% parameters
x      = -50:1:50;    % domain
saltR  = 5e5;         % noise range ±500 000
winSz  = 5;           % smoothing half-window

% filenames
f1 = "data.csv";
f2 = "salted_data.csv";
f3 = "smoothed_data.csv";

% 1) generate original curve
generate_data(x, f1);

% 2) salt it
y_s = salt_data(f1, saltR, f2);

% 3) smooth the salted
y_sm = smooth_data(f2, winSz, f3);

% 4) load for plotting
D1 = csvread(f1);
D2 = csvread(f2);
D3 = csvread(f3);

figure; hold on; grid on;
plot(D1(:,1), D1(:,2), "-k", "LineWidth", 1.5, "DisplayName", "Original");
plot(D2(:,1), D2(:,2), "-b", "DisplayName", "Salted");
plot(D3(:,1), D3(:,2), "-r", "DisplayName", "Smoothed");
xlabel("x"); ylabel("y");
title("Pipeline in Octave: x^4 → salt → smooth");
legend("Location","northwest");

% save the figure
print("pipeline_plot.png","-dpng");

% 5) compute & save bounds [min max] for each stage
origY = D1(:,2);
saltY = D2(:,2);
smY   = D3(:,2);
bounds = [min(origY), max(origY);
          min(saltY), max(saltY);
          min(smY),   max(smY)];
csvwrite("bounds_table.csv", bounds);

disp("Bounds rows = [orig; salted; smoothed], cols = [min max]:");
disp(bounds);

