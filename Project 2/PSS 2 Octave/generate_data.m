% generate_data.m
function generate_data(x, outFile)
  %GENERATE_DATA  Build y = x.^4, save to CSV
  y = x .^ 4;
  data = [x(:), y(:)];
  csvwrite(outFile, data);
  fprintf("Wrote %s   (%.0f pts, y-range [%.0f, %.0f])\n",
          outFile, numel(x), min(y), max(y));
end

