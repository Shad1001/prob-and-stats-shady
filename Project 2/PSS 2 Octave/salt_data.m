% salt_data.m
function y_s = salt_data(inFile, saltRange, outFile)
  %SALT_DATA  Load CSV, add uniform noise ±saltRange to y, save CSV
  D = csvread(inFile);
  x = D(:,1);
  y = D(:,2);
  noise = (rand(size(y)) * 2*saltRange) - saltRange;
  y_s = y + noise;
  csvwrite(outFile, [x, y_s]);
  fprintf("Wrote %s   y-range [%.0f, %.0f]\n",
          outFile, min(y_s), max(y_s));
end

